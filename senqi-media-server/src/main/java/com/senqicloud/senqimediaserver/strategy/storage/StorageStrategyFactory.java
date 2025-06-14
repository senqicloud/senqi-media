package com.senqicloud.senqimediaserver.strategy.storage;

import com.senqicloud.senqimediaserver.enums.StorageType;
import com.senqicloud.senqimediaserver.exception.ServerErrorException;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class StorageStrategyFactory {

    List<StorageStrategy> strategies;

    // 所有实现了 StorageStrategy 的 Bean 会自动注入
    public StorageStrategyFactory(List<StorageStrategy> strategies) {
        this.strategies = strategies;
    }

    public StorageStrategy getStrategy(StorageType storageType) {
        return strategies.stream()
                .filter(s -> s.supports(storageType))
                .findFirst()
                .orElseThrow(() -> new ServerErrorException("不支持的存储方式"));
    }
}
