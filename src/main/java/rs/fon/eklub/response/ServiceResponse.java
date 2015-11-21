/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.fon.eklub.response;

/**
 *
 * @author milos
 */
public class ServiceResponse {
    
    private String responseStatus;
    private String responseMessage;
    private String requestUri;

    public ServiceResponse() {
    }

    public ServiceResponse(String responseStatus, String responseMessage, String requestUri) {
        this.responseStatus = responseStatus;
        this.responseMessage = responseMessage;
        this.requestUri = requestUri;
    }

    public String getResponseStatus() {
        return responseStatus;
    }

    public void setResponseStatus(String responseStatus) {
        this.responseStatus = responseStatus;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }

    public String getRequestUri() {
        return requestUri;
    }

    public void setRequestUri(String requestUri) {
        this.requestUri = requestUri;
    }
}
