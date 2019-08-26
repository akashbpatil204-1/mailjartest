package com.loginportal.utils.otp;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.cloud.aws.mail.simplemail.SimpleEmailServiceMailSender;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClientBuilder;

//@SpringBootApplication
//@Configuration
public class AwsSesOtpApplication {
	
	/*
	 * @Bean public AmazonSimpleEmailService
	 * amazonSimpleEmailService(AWSCredentialsProvider credentialsProvider) { return
	 * AmazonSimpleEmailServiceClientBuilder.standard()
	 * .withCredentials(credentialsProvider) // Replace US_WEST_2 with the AWS
	 * Region you're using for // Amazon SES.
	 * .withRegion(Regions.US_EAST_1).build(); }
	 * 
	 * @Bean public MailSender mailSender(AmazonSimpleEmailService ses) { return new
	 * SimpleEmailServiceMailSender(ses); }
	 */
    @Autowired
	static
    AWSCredentialsProvider credentialsProvider;
    
	@Autowired
	private static MailSender mailSender;
	
	private static SimpleMailMessage message=new SimpleMailMessage();;

	@Autowired
	public AwsSesOtpApplication(MailSender mailSender) {
		System.out.println("constructor");
	}

	public static void main(String[] args) {
		System.out.println("hai");
		//ConfigurableApplicationContext context = SpringApplication.run(AwsSesOtpApplication.class, args);
		System.out.println("hai after");
		try {
			doSendEmailWith();
		} finally {
		
		}
	}

	public static void doSendEmailWith() {
		MailSender sender = new SimpleEmailServiceMailSender(AmazonSimpleEmailServiceClientBuilder.standard()
                .withCredentials(credentialsProvider)
                // Replace US_WEST_2 with the AWS Region you're using for
                // Amazon SES.
                .withRegion(Regions.US_EAST_1).build());

		
		 setMailFrom("akashbpatil204@gmail.com"); 
		 setMailTo("akashbpatil204@gmail.com");
		System.out.println("initial");
	 
		Integer x = (int)((Math.random() * (10000 - 1000)) + 1000);
		String y = Integer.toString(x);
		setSubject("");
		message.setText(y+" is your OTP. Please do not share with anyone ");
		
		sender.send(message);
		System.out.println("Message Sent");
	}

	public static void setMailFrom(String email) {
		message.setFrom(email);
	}

	public static void setMailTo(String email) {
		message.setTo(email);
	}
	
	public static void setText(String text) {
		message.setText(text);
	}
	
	public static void setSubject(String sub) {
		message.setSubject(sub);
	}
	
}
