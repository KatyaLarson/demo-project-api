package com.demo.api.test;
import static io.restassured.RestAssured.given;

import com.demo.api.pojo.PUTFruit;
import com.demo.api.pojo.Product;
import com.demo.api.utilities.FruitShopTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.*;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

public class FruitShopTask extends FruitShopTestBase {

    /*
       API endpoint is: https://api.predic8.de/shop/
       API documentation: https://api.predic8.de/shop/docs
   
        Add a new product for an existing vendor
        Update the name and price of the product
        List the product
        Add a photo of the product
        Display the photo for the product
        Delete the product
   
        */
    static Product fruit1;

    static PUTFruit fruitPut;
    static int id;

    @DisplayName("Add a new product for an existing vendor")
    @Order(1)
    @Test
    @Tag("regression")
    public void postNewProduct(){
        Map<String,Object> randomFruit=new HashMap<>();

        randomFruit.put("name","Pineapple");
        randomFruit.put("price",6.52);
        randomFruit.put("category","Fruits");

        JsonPath jsonPath =given().accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .pathParam("id",672)
                .body(randomFruit)
                .when().post("/vendors/{id}/products/")
                .then().statusCode(201).extract().jsonPath();

        fruit1 =jsonPath.getObject("", Product.class);
        System.out.println(fruit1);


        id=fruit1.setId();
        System.out.println(id);
     /*  category_url=fruit1.getCategory_url();
       vendor_url=fruit1.getVendor_url();
        System.out.println(vendor_url);*/

    }


    @DisplayName("Update the name and price of the product")
    @Order(2)
    @Test

    public void updateProduct(){


        fruitPut=new PUTFruit();
        fruitPut.setName("Cranberries");
        fruitPut.setPrice(30.99);
        fruitPut.setCategory_url(fruit1.getCategory_url());
        fruitPut.setVendor_url(fruit1.getVendor_url());

        System.out.println(fruitPut);
        given().accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .pathParam("id",id)
                .body(fruitPut)
                .when().put("/products/{id}").prettyPeek()
                .then().statusCode(200);



    }

    @DisplayName("List the product")
    @Order(3)
    @Test

    public void listProduct(){




        given().accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .pathParam("id",id)

                .when().get("/products/{id}")
                .then().statusCode(200);



    }

    @DisplayName("Add photo of the Product")
    @Order(4)
    @Test

    public void AddPhotoOfProduct(){

        File testUploadFile=new File("src/test/resources/cranberries.jpg");

        given().log().all().accept(ContentType.MULTIPART)
                .contentType(ContentType.MULTIPART)
                .pathParam("id",id)
                .multiPart("file",testUploadFile)
                .when().put("/products/{id}/photo").prettyPeek()
                .then().statusCode(201);



    }

    @DisplayName("Display the foto of the product")
    @Order(5)
    @Test

    public void displayPhotoOfProduct(){




        given().accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .pathParam("id",id)

                .when().get("/products/{id}/photo")
                .then().statusCode(200);



    }

    @DisplayName("Delete the product")
    @Order(6)
    @Test

    public void deleteTheProduct(){




        given()

                .pathParam("id",id)

                .when().delete("/products/{id}")
                .then().statusCode(200);



    }


}
