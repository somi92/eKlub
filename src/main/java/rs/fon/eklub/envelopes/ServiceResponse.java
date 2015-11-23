/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.fon.eklub.envelopes;

/**
 *
 * @author milos
 */
public class ServiceResponse<T> {
    
    private String status;
    private String message;
    private String requestUri;
    private T payload;

    public ServiceResponse() {
    }

    public ServiceResponse(String status, String message, String requestUri, T payload) {
        this.status = status;
        this.message = message;
        this.requestUri = requestUri;
        this.payload = payload;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getRequestUri() {
        return requestUri;
    }

    public void setRequestUri(String requestUri) {
        this.requestUri = requestUri;
    }

    public T getPayload() {
        return payload;
    }

    public void setPayload(T payload) {
        this.payload = payload;
    }
}
