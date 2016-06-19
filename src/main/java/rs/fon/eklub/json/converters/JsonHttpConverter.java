/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.fon.eklub.json.converters;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.text.SimpleDateFormat;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Component;
import rs.fon.eklub.constants.ServiceAPI;
import rs.fon.eklub.core.entities.Attendance;
import rs.fon.eklub.core.entities.Member;
import rs.fon.eklub.core.entities.Payment;
import rs.fon.eklub.core.entities.Training;
import rs.fon.eklub.envelopes.ServiceResponse;
import rs.fon.eklub.json.mixins.AttendanceForMemberSerializationMixin;
import rs.fon.eklub.json.mixins.AttendanceForTrainingSerializationMixin;
import rs.fon.eklub.json.mixins.MemberSerializationMixin;
import rs.fon.eklub.json.mixins.PaymentSerializationMixin;
import rs.fon.eklub.json.mixins.TrainingSerializationMixin;

/**
 *
 * @author milos
 */
@Component
public class JsonHttpConverter extends MappingJackson2HttpMessageConverter {

    @Override
    protected void writeInternal(Object object, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {

        ObjectMapper mapper = new ObjectMapper();
        JsonGenerator jsonGenerator = mapper.getFactory().createGenerator(outputMessage.getBody());
        try {
            
            if(object instanceof ServiceResponse) {
                ServiceResponse res = (ServiceResponse) object;
                String requestUri = res.getRequestUri();
                
                if(requestUri.startsWith(ServiceAPI.Member.MEMBER_ROOT)) {
                    mapper.addMixIn(Attendance.class, AttendanceForMemberSerializationMixin.class);
                    mapper.addMixIn(Payment.class, PaymentSerializationMixin.class);
                    mapper.addMixIn(Training.class, TrainingSerializationMixin.class);
                }
                if(requestUri.startsWith(ServiceAPI.Training.TRAINING_ROOT)) {
                    mapper.addMixIn(Attendance.class, AttendanceForTrainingSerializationMixin.class);
                    mapper.addMixIn(Member.class, MemberSerializationMixin.class);
                }
                if(requestUri.startsWith(ServiceAPI.Payment.PAYMENT_ROOT)) {
                    mapper.addMixIn(Member.class, MemberSerializationMixin.class);
                }
                
                mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss"));
                mapper.writeValue(jsonGenerator, object);
            } else if(object == null) {
                jsonGenerator.writeNull();
            } else {
                mapper.writeValue(jsonGenerator, object);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
