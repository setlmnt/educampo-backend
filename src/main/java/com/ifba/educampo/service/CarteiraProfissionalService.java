package com.ifba.educampo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import com.ifba.educampo.domain.CarteiraProfissional;
import com.ifba.educampo.exception.BadRequestException;
import com.ifba.educampo.repository.CarteiraProfissionalRepository;
import com.ifba.educampo.requests.CarteiraProfissionalPostRequestBody;
import com.ifba.educampo.requests.CarteiraProfissionalPutRequestBody;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

import lombok.RequiredArgsConstructor;
import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class CarteiraProfissionalService {
	@Autowired
	private CarteiraProfissionalRepository carteiraRepository;
	
	public CarteiraProfissional findCarteira(Long id) {
		return carteiraRepository.findById(id)
				.orElseThrow(()-> new BadRequestException("Carteira Not Found"));
	}
	
	public List<CarteiraProfissional> listAll() {
        return carteiraRepository.findAll();
    }
	
	public void delete(long id) {
		carteiraRepository.delete(findCarteira(id));
	}
	
	@Transactional
	public CarteiraProfissional save(CarteiraProfissionalPostRequestBody carteiraPostRequestBody) {
		return carteiraRepository.save(CarteiraProfissional.builder()
						.numero(carteiraPostRequestBody.getNumero())
						.serie(carteiraPostRequestBody.getSerie())
						.build()
				);
	}
	
	public CarteiraProfissional replace(CarteiraProfissionalPutRequestBody carteiraPutRequestBody, Long carteiraId) {
		CarteiraProfissional savedCarteira = findCarteira(carteiraId);
		return carteiraRepository.save(CarteiraProfissional.builder()
										.id(savedCarteira.getId())
										.numero(carteiraPutRequestBody.getNumero())
										.serie(carteiraPutRequestBody.getSerie())
										.build());
		
	}
	
	public void updateByFields(long id, Map<String, Object> fields) {
		CarteiraProfissional savedCarteira = findCarteira(id);
		
		fields.forEach((key,value)->{
			Field field = ReflectionUtils.findField(CarteiraProfissional.class, key);
			field.setAccessible(true);
			ReflectionUtils.setField(field, savedCarteira, value);
		});
		carteiraRepository.save(savedCarteira);
	}
	
}
