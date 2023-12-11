package br.pro.delfino.drogaria.service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import br.pro.delfino.drogaria.api.exceptionhandler.RecursoNaoEncontradoException;
import br.pro.delfino.drogaria.domain.MD5Cryptography;
import br.pro.delfino.drogaria.domain.Usuario;
import br.pro.delfino.drogaria.repository.UsuarioRepository;
import br.pro.delfino.drogaria.request.LoginPostRequest;
import br.pro.delfino.drogaria.response.LoginResponse;
import br.pro.delfino.drogaria.security.JwtSecurity;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class LoginService {

	@Autowired
	private UsuarioRepository usuarioRepositorio;
	
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
			String token = Jwts.builder().setId("DROGARIA_JWT").setSubject(emailUsuario)
					.claim("authorities",
							grantedAuthorities.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
					.setIssuedAt(new Date(System.currentTimeMillis()))
					.setExpiration(new Date(System.currentTimeMillis() + 6000000))
					.signWith(SignatureAlgorithm.HS512, JwtSecurity.SECRET.getBytes()).compact();

			return token;

		}
		
		
		public Usuario cadastrarUsuario(Usuario usuario) {
			
			usuario.setSenha(MD5Cryptography.encrypt(usuario.getSenha()));
			if(usuarioRepositorio.findByEmail(usuario.getEmail())!= null) {
				throw new RecursoNaoEncontradoException("O email informado ja esta cadastrado.");
			}
			return usuarioRepositorio.save(usuario);
		}
		
}