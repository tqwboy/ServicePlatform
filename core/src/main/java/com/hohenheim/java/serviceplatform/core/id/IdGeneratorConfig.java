package com.hohenheim.java.serviceplatform.core.id;

import lombok.Data;

/**
 * @author Hohenheim
 * @date 2022/7/16
 * @description ID生成器配置
 */
@Data
public class IdGeneratorConfig {
    private String baseTime;

    public short workerId = 0;

    public byte workerIdBitLength = 6
            ;
    public byte seqBitLength = 6;

    public short maxSeqNumber = 0;

    public short minSeqNumber = 5;
}