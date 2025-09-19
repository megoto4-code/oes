package oes.data;

public class jdbcEx {

	public static void main(String[] args) {
		jdbcConnection jc = new jdbcConnection();
		jc.setAttributeValue("user", "password", "1", "tonumoi");
		System.out.println("Username:" + jc.getAttributeValue("user", "username", "1"));
		System.out.println("Password:" + jc.getAttributeValue("settings", "value", "1"));
		System.out.println(Math.round(55.53456) / 100.0f);
	}

}
