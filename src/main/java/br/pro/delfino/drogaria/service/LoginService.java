package br.pro.delfino.drogaria.service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.pro.delfino.drogaria.api.exceptionhandler.RecursoNaoEncontradoException;
import br.pro.delfino.drogaria.domain.MD5Cryptography;
import br.pro.delfino.drogaria.domain.Usuario;
import br.pro.delfino.drogaria.dto.request.LoginPostRequest;
import br.pro.delfino.drogaria.dto.request.UsuarioMessageDTO;
import br.pro.delfino.drogaria.dto.request.UsuarioRequestDTO;
import br.pro.delfino.drogaria.dto.response.LoginResponse;
import br.pro.delfino.drogaria.repository.UsuarioRepository;
import br.pro.delfino.drogaria.security.JwtSecurity;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class LoginService {

	@Autowired
	private UsuarioRepository usuarioRepositorio;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@Autowired
	private LoginMessageProducer loginMessageProducer;
	
	public ResponseEntity<LoginResponse>acessar(@RequestBody LoginPostRequest request){
		try {
			LoginResponse response = new LoginResponse();
			Usuario usuario = usuarioRepositorio.findByEmailAndSenha(request.getEmail(), MD5Cryptography.encrypt(request.getSenha()));
			
			if(usuario!=null) {
				response.setStatusCode(200);
				response.setMensagem("Usuário autenticado com sucesso.");
				response.setAcessToken(getAccessToken(usuario.getEmail()));
				response.setNomeUsuario(usuario.getNome());
				response.setEmailUsuario(usuario.getEmail());
				return ResponseEntity.status(HttpStatus.OK)
						.body(response); 
			}else {
				response.setStatusCode(401);
				response.setMensagem("Acesso negado");
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
						.body(response); 
			}
			
		} catch (Exception e) {
			
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}
	
	
	
	// método para gerar o ACCESS TOKEN do usuário
		private String getAccessToken(String emailUsuario) {

			List<GrantedAuthority> grantedAuthorities = AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_USER");

			// COTI_JWT -> nome da aplicação que gerou o token!
			return Jwts.builder().setId("DROGARIA_JWT").setSubject(emailUsuario)
					.claim("authorities",
							grantedAuthorities.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
					.setIssuedAt(new Date(System.currentTimeMillis()))
					.setExpiration(new Date(System.currentTimeMillis() + 6000000))
					.signWith(SignatureAlgorithm.HS512, JwtSecurity.SECRET.getBytes()).compact();

		}
		
		
		public Usuario cadastrarUsuario(UsuarioRequestDTO dto) {
			
			if(usuarioRepositorio.findByEmail(dto.getEmail())!= null) {
				throw new RecursoNaoEncontradoException("O email informado ja esta cadastrado.");
			}
			Usuario usuario = modelMapper.map(dto, Usuario.class);
			usuario.setSenha(MD5Cryptography.encrypt(dto.getSenha()));
			createWelcomeMessage(usuario);
			return usuarioRepositorio.save(usuario);
		}
		
		
		private void createWelcomeMessage(Usuario usuario) {

			UsuarioMessageDTO dto = new UsuarioMessageDTO();
			dto.setEmailTo(usuario.getEmail());
			dto.setSubject("Conta criada com sucesso - API Drogaria.");

			StringBuilder sb = new StringBuilder();
			sb.append("<div>");
			sb.append("<p>Parabéns " + usuario.getNome() + ", sua conta de usuário foi criada com sucesso</p>");
			sb.append("<p>Att,</p>");
			sb.append("<p>Equipe DBS informática,</p>");
			sb.append("</div>");
			
			dto.setBody(sb.toString());
			
			try {
				String message = objectMapper.writeValueAsString(dto);
				loginMessageProducer.sendMessage(message);
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		
}
