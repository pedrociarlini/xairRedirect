package br.com.pedrociarlini.xairedirect;

import java.awt.EventQueue;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

import br.com.pedrociarlini.xairedirect.ui.MainFrame;

@SpringBootApplication
public class XairRedirectApplication {

	public static void main(String[] args) {
		var ctx = new SpringApplicationBuilder(XairRedirectApplication.class).headless(false).run(args);
		// SpringApplication.run(XairRedirectApplication.class, args);
		EventQueue.invokeLater(() -> {

			var ex = ctx.getBean(MainFrame.class);
			ex.setVisible(true);
		});
	}

}
