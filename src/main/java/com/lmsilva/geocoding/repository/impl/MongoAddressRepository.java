package com.lmsilva.geocoding.repository.impl;

import com.lmsilva.geocoding.data.Address;
import com.lmsilva.geocoding.repository.AddressRepository;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoClientURI;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


import javax.annotation.PostConstruct;

import static com.mongodb.client.model.Filters.eq;

@Component
public class MongoAddressRepository implements AddressRepository {

    @Value("${mongodb.host}")
    private String host;

    @Value("${mongodb.port}")
    private int port;

    @Value("${mongodb.database}")
    private String database;

    private MongoCollection<Address> collection;

    // post construct will have the values from application.properties
    @PostConstruct
    public void postConstruct() {
        CodecRegistry pojoCodecRegistry = CodecRegistries.fromRegistries(MongoClient.getDefaultCodecRegistry(),
                CodecRegistries.fromProviders(PojoCodecProvider.builder().automatic(true).build()));

        ServerAddress serverAddress = new ServerAddress(host, port);
        MongoClient client = new MongoClient(serverAddress, MongoClientOptions.builder().codecRegistry(pojoCodecRegistry).build());
        MongoDatabase db = client.getDatabase(database);
        collection = db.getCollection("data", Address.class);
    }

    public Address createAddress(Address address) {
        collection.insertOne(address);
        return address;
    }

    public Address readAddress(Address address) {
        return collection.find(eq("id", address.getId())).first();
    }

    public Address updateAddress(Address address) {
        collection.replaceOne(eq("id", address.getId()), address);
        return address;
    }

    public void deleteAddress(Address address) {
        collection.deleteOne(eq("id", address.getId()));
    }
}
