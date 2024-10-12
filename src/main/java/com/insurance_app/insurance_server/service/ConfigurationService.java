package com.insurance_app.insurance_server.service;

import com.insurance_app.insurance_server.repository.ConfigurationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.insurance_app.insurance_server.entity.ConfigurationEntity;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ConfigurationService {

    private final ConfigurationRepository configurationRepository;

    public <T> T getByKey(String key, Class<T> type) {
        Optional<ConfigurationEntity> byKey = configurationRepository.findByKey(key);
        if (byKey.isPresent()) {
            ConfigurationEntity configurationEntity = byKey.get();
            if (type.isAssignableFrom(Integer.class)) {
                return type.cast(Integer.parseInt(configurationEntity.getValue()));
            } else if (type.isAssignableFrom(String.class)) {
                return type.cast(configurationEntity.getValue());
            }
        }
        return null;
    }
}
