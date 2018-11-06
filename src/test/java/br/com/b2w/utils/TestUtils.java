package br.com.b2w.utils;
import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.b2w.entities.IGenericEntity;

public class TestUtils {

	public static <T> T jsonToList(final TypeReference<T> type, final String jsonPacket) {
		T data = null;

		try {
			data = new ObjectMapper().readValue(jsonPacket, type);
		} catch (Exception e) {
			// Handle the problem
		}
		return data;
	}

	public static String objectToJson(IGenericEntity obj) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper(); 
		String jsonResult = mapper.writeValueAsString(obj);
		return jsonResult;
	}

	public static <T extends IGenericEntity> T jsonToObject(String json, Class<T> classOf) throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		T entity = mapper.readValue(json, classOf);
		return entity;
	}
}
