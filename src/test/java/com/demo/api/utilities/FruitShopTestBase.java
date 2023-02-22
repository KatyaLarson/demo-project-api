package com.demo.api.utilities;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.jupiter.api.BeforeAll;

public class FruitShopTestBase {
    public static RequestSpecification reqSpec;
    public static RequestSpecification reqUserSpec;

    public static ResponseSpecification resSpec;


    @BeforeAll
    public static void init(){
        RestAssured.baseURI="https://api.predic8.de";
        RestAssured.basePath="/shop";





    }


}
