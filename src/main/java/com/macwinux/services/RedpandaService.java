package com.macwinux.services;

import com.macwinux.Main;
import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer;
import org.apache.log4j.Logger;

import java.util.Properties;

public final class RedpandaService {
    private static final Logger LOGGER = Logger.getLogger(Main.class);

    /**
     * Creating environment for kafka that consume stream message from kafka topic.
     *
     * @param environment  Flink Stream Execution Environment.
     * @return DataStream of type string.
     */
    public final DataStream<String> rpStreamConsumer(final StreamExecutionEnvironment environment) {

        LOGGER.info("Open Redpanda connection and Streaming car data through topic.");
        // Set more properties like offset, Checkpointing etc as per requirement.
        Properties properties = new Properties();
        properties.setProperty("bootstrap.servers", "localhost:9092");
        properties.setProperty("group.id", "testRP");

        return environment.addSource(new FlinkKafkaConsumer<>("car.create",
                        new SimpleStringSchema(), properties).setStartFromEarliest());

    }
}
