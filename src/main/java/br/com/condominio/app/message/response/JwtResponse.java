package br.com.condominio.app.message.response;

import java.util.List;

public class JwtResponse {
	private String username;
	private List<String> roles;
	
    private String token;
    private String type = "Bearer";

       
    public JwtResponse(String token, String username, List<String> roles) {
		this.username = username;
		this.roles = roles;
		this.token = token;
	}

	public JwtResponse(String accessToken) {
        this.token = accessToken;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String accessToken) {
        this.token = accessToken;
    }

    public String getTokenType() {
        return type;
    }

    public void setTokenType(String tokenType) {
        this.type = tokenType;
    }

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<String> getRoles() {
		return roles;
	}

	public void setRoles(List<String> roles) {
		this.roles = roles;
	}
    
    
    

}
