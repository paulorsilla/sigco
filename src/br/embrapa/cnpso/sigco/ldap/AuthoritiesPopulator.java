package br.embrapa.cnpso.sigco.ldap;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

import org.springframework.ldap.core.DirContextOperations;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.ldap.userdetails.LdapAuthoritiesPopulator;
import org.springframework.stereotype.Component;

import br.embrapa.cnpso.sigco.model.Usuario;

@Component("AuthoritiesPopulator")
public class AuthoritiesPopulator implements LdapAuthoritiesPopulator {
	
	@PersistenceContext(type=PersistenceContextType.TRANSACTION)
	private EntityManager entityManager;
	
	@Override
	public Collection<GrantedAuthority> getGrantedAuthorities(
			DirContextOperations userData, String username) {

		ArrayList<GrantedAuthority> userPerms = new ArrayList<GrantedAuthority>();
		Usuario usuario = this.entityManager.find(Usuario.class, username);
		System.out.println(usuario.getNomeCompleto());
		System.out.println(usuario.getAutorizacao().getNome());
		userPerms.add(new SimpleGrantedAuthority(usuario.getAutorizacao().getNome()));
		return userPerms;
	}

}
