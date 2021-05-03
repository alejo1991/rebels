package com.mercadolibre.starWars.rebels.validator.implm;

import java.beans.PropertyEditor;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.PropertyEditorRegistry;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import com.mercadolibre.starWars.rebels.dto.response.ValidationErrorDTO;

public class BaseValidator {
	
	protected BindingResult getEmptyBindingResult(List<ValidationErrorDTO> errorList) {
		
		BindingResult bindingResult = new BindingResult() {
			
			@Override
			public void setNestedPath(String nestedPath) {				
			}
			
			@Override
			public void rejectValue(String field, String errorCode, Object[] errorArgs, String defaultMessage) {
			}
			
			@Override
			public void rejectValue(String field, String errorCode, String defaultMessage) {
				
				
			}
			
			@Override
			public void rejectValue(String field, String errorCode) {
				
				
			}
			
			@Override
			public void reject(String errorCode, Object[] errorArgs, String defaultMessage) {
				
				
			}
			
			@Override
			public void reject(String errorCode, String defaultMessage) {
				
				
			}
			
			@Override
			public void reject(String errorCode) {
				
				
			}
			
			@Override
			public void pushNestedPath(String subPath) {
				
				
			}
			
			@Override
			public void popNestedPath() throws IllegalStateException {
				
				
			}
			
			@Override
			public boolean hasGlobalErrors() {
				
				return false;
			}
			
			@Override
			public boolean hasFieldErrors(String field) {
				
				return false;
			}
			
			@Override
			public boolean hasFieldErrors() {
				
				return false;
			}
			
			@Override
			public boolean hasErrors() {
				
				return false;
			}
			
			@Override
			public String getObjectName() {
				
				return null;
			}
			
			@Override
			public String getNestedPath() {
				
				return null;
			}
			
			@Override
			public List<ObjectError> getGlobalErrors() {
				
				return null;
			}
			
			@Override
			public int getGlobalErrorCount() {
				
				return 0;
			}
			
			@Override
			public ObjectError getGlobalError() {
				
				return null;
			}
			
			@Override
			public Object getFieldValue(String field) {
				
				return null;
			}
			
			@Override
			public Class<?> getFieldType(String field) {
				
				return null;
			}
			
			@Override
			public List<FieldError> getFieldErrors(String field) {
				
				return null;
			}
			
			@Override
			public List<FieldError> getFieldErrors() {
				
				return null;
			}
			
			@Override
			public int getFieldErrorCount(String field) {
				
				return 0;
			}
			
			@Override
			public int getFieldErrorCount() {
				
				return 0;
			}
			
			@Override
			public FieldError getFieldError(String field) {
				
				return null;
			}
			
			@Override
			public FieldError getFieldError() {
				
				return null;
			}
			
			@Override
			public int getErrorCount() {
				
				return 0;
			}
			
			@Override
			public List<ObjectError> getAllErrors() {
				
				return new ArrayList<>();
			}
			
			@Override
			public void addAllErrors(Errors errors) {
				
				
			}
			
			@Override
			public String[] resolveMessageCodes(String errorCode, String field) {
				
				return null;
			}
			
			@Override
			public String[] resolveMessageCodes(String errorCode) {
				
				return null;
			}
			
			@Override
			public Object getTarget() {
				
				return null;
			}
			
			@Override
			public Object getRawFieldValue(String field) {
				
				return null;
			}
			
			@Override
			public PropertyEditorRegistry getPropertyEditorRegistry() {
				
				return null;
			}
			
			@Override
			public Map<String, Object> getModel() {
				
				return null;
			}
			
			@Override
			public PropertyEditor findEditor(String field, Class<?> valueType) {
				
				return null;
			}
			
			@Override
			public void addError(ObjectError error) {
				
				
			}
		};
		
		return bindingResult;
	}

}
