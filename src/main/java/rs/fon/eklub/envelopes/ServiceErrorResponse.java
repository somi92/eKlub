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
public class ServiceErrorResponse {
    
    private String errorType;
    private String errorMessage;
    private String requestUri;

    public ServiceErrorResponse() {
    }

    public ServiceErrorResponse(String errorType, String errorMessage, String requestUri) {
        this.errorType = errorType;
        this.errorMessage = errorMessage;
        this.requestUri = requestUri;
    }

    public String getErrorType() {
        return errorType;
    }

    public void setErrorType(String errorType) {
        this.errorType = errorType;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getRequestUri() {
        return requestUri;
    }

    public void setRequestUri(String requestUri) {
        this.requestUri = requestUri;
    }
    
    
}
