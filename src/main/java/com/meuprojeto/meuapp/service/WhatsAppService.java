package com.meuprojeto.meuapp.service;
import org.springframework.stereotype.Service;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;


@Service
public class WhatsAppService {
    
    
    private static final String ACCOUNT_SID = "";
    private static final String AUTH_TOKEN = "";
    private static final String FROM_PHONE_NUMBER = ""; 
    private static final String TO_PHONE_NUMBER = "";  

    public void enviarMensagemWhatsApp(String mensagem) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        Message message = Message.creator(
                new com.twilio.type.PhoneNumber(TO_PHONE_NUMBER),
                new com.twilio.type.PhoneNumber(FROM_PHONE_NUMBER),
                mensagem
        ).create();

        System.out.println("Mensagem enviada com SID: " + message.getSid());
    }
}