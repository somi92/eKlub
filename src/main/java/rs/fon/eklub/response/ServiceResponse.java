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
public class ServiceResponse<T> {
    
    private String responseStatus;
    private String responseUri;
    private T responseContent;

    public ServiceResponse() {
    }

    public ServiceResponse(String responseStatus, String requestUri, T responseContent) {
        this.responseStatus = responseStatus;
        this.responseUri = requestUri;
        this.responseContent = responseContent;
    }

    public String getResponseStatus() {
        return responseStatus;
    }

    public void setResponseStatus(String responseStatus) {
        this.responseStatus = responseStatus;
    }

    public String getResponseUri() {
        return responseUri;
    }

    public void setResponseUri(String responseUri) {
        this.responseUri = responseUri;
    }

    public T getResponseContent() {
        return responseContent;
    }

    public void setResponseContent(T responseContent) {
        this.responseContent = responseContent;
    }
    
    
}
