package com.example;

import javax.xml.namespace.QName;
import javax.xml.soap.*;

public class NalogServices {

    private static String namespaceURI = "http://ws.unisoft/FNSNDSCAWS2/Request";
    private static String soapUrl      = "http://npchk.nalog.ru/FNSNDSCAWS_2?wsdl";
    private static String serviceName  = "NdsRequest2";
    private static String namespace  = "ns1"; // Namespace";
    private static String soapAction = namespaceURI + "/" + serviceName;

    public static String getContractorState(String INN) throws  SOAPException {

        System.setProperty("javax.xml.soap.SAAJMetaFactory", "com.sun.xml.messaging.saaj.soap.SAAJMetaFactoryImpl");
        SOAPConnection soapConnect = SOAPConnectionFactory.newInstance().createConnection();
        SOAPMessage soapMessage = getContractorStateSoapMessage(INN);

        SOAPMessage soapResponse = soapConnect.call(soapMessage, soapUrl);

        SOAPElement soapElement = (SOAPElement) soapResponse.getSOAPBody().getChildElements().next();
        soapElement = (SOAPElement) soapElement.getChildElements().next();

        String state = soapElement.getAttributeValue(new QName("State"));
        return state;

    }

    private static SOAPMessage getContractorStateSoapMessage(String INN) throws SOAPException {

        SOAPMessage soapMessage = MessageFactory.newInstance().createMessage();

        SOAPPart soapPart = soapMessage.getSOAPPart();

        SOAPEnvelope envelope = soapPart.getEnvelope();
        envelope.addNamespaceDeclaration(namespace, namespaceURI);

        SOAPBody soapBody = envelope.getBody();
        SOAPElement soapBodyElem  = soapBody.addChildElement(serviceName, namespace);
        SOAPElement soapBodyElem1 = soapBodyElem.addChildElement("NP", namespace);
        soapBodyElem1.addAttribute(new QName("INN"), INN);

        return soapMessage;

    }

}
