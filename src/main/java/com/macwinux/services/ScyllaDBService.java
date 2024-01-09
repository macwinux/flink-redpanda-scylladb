package com.macwinux.services;

import com.datastax.driver.mapping.Mapper;
import com.macwinux.Main;
import model.Car;


import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.common.typeinfo.TypeInformation;
import org.apache.flink.api.java.tuple.Tuple3;
import org.apache.flink.api.java.typeutils.TupleTypeInfo;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.connectors.cassandra.CassandraSink;
import org.apache.log4j.Logger;

/**
 * CassandraService is a class that sinks DataStream into ScyllaDB.
 */
public final class ScyllaDBService {

    private static final Logger LOGGER = Logger.getLogger(Main.class);

    /**
     * Creating environment for ScyllaDB and sink some Data of car stream into ScyllaDB
     *
     * @param sinkCarStream  DataStream of type Car.
     */
    public final void sinkToScyllaDB(final DataStream<Car> sinkCarStream) throws Exception {

        LOGGER.info("Creating car data to sink into scyllaDB.");
        SingleOutputStreamOperator<Tuple3<String, Long, Long>> sinkCarDataStream = sinkCarStream.map((MapFunction<Car, Tuple3<String, Long, Long>>) car ->
                new Tuple3<>(car.getName(),car.getCylinders(),car.getHorsepower()))
                .returns(new TupleTypeInfo<>(TypeInformation.of(String.class), TypeInformation.of(Long.class), TypeInformation.of(Long.class)));

        LOGGER.info("Open ScyllaDB connection and Sinking car data into ScyllaDB.");
        CassandraSink.addSink(sinkCarDataStream)
                .setHost("scylla",9042)
                .setQuery("INSERT INTO example.car(Name, Cylinders, Horsepower) values (?, ?, ?);")
                .setMapperOptions(() -> new Mapper.Option[]{Mapper.Option.saveNullFields(true)})
                .build();

    }
}