package com.hohenheim.java.serviceplatform.core.id;

import com.github.yitter.contract.IIdGenerator;
import com.github.yitter.contract.IdGeneratorOptions;
import com.github.yitter.idgen.DefaultIdGenerator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

/**
 * @author Hohenheim
 * @date 2022/7/16
 * @description
 */
@Configuration
public class IdGeneratorFactory {
    @Bean("generalIdGeneratorConfig")
    @ConfigurationProperties(prefix = "id-generator.general-id-generator")
    @Primary
    public IdGeneratorConfig generalIdGeneratorConfig() {
        return new IdGeneratorConfig();
    }

    @Bean("generalIdGenerator")
    @Primary
    public IIdGenerator generalIdGenerator(@Qualifier("generalIdGeneratorConfig") IdGeneratorConfig config) {
        return createIdGenerator(config);
    }

    @Bean("userIdGeneratorConfig")
    @ConfigurationProperties(prefix = "id-generator.user-id-generator")
    public IdGeneratorConfig userIdGeneratorConfig() {
        return new IdGeneratorConfig();
    }

    @Bean("userIdGenerator")
    public IIdGenerator userIdGenerator(@Qualifier("userIdGeneratorConfig") IdGeneratorConfig config) {
        return createIdGenerator(config);
    }

    private IIdGenerator createIdGenerator(IdGeneratorConfig config) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");

        IdGeneratorOptions options = new IdGeneratorOptions();
        options.WorkerId = config.getWorkerId();
        options.WorkerIdBitLength = config.getWorkerIdBitLength();
        options.SeqBitLength = config.getSeqBitLength();
        options.MaxSeqNumber = config.getMaxSeqNumber();
        options.MinSeqNumber = config.getMinSeqNumber();
        options.BaseTime = LocalDateTime.parse(config.getBaseTime(), formatter)
                .toInstant(ZoneOffset.of("+8"))
                .toEpochMilli();

        return new DefaultIdGenerator(options);
    }
}
