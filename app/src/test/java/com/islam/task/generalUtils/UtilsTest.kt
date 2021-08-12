package com.islam.task.generalUtils

import com.islam.task.data.entity.ItemModel
import org.json.JSONObject
import org.junit.Assert
import org.junit.Test

class UtilsTest {

    @Test
    fun `convertJsonToArray() with full object then return full list`() {

        //arrange
        val startingJson = "{\n" +
                "\"107\": \"First\",\n" +
                "\"125\": \"Second\",\n" +
                "\"130\": \"Third\",\n" +
                "}"

        val stringJsonObj = JSONObject(startingJson)
        val list = mutableListOf<ItemModel>()
        for (key in stringJsonObj.keys()) {
            val value = stringJsonObj.opt(key as String) as String
            list.add(ItemModel(key, value))
        }

        //act
        val result = Utils.convertJsonToArray(stringJsonObj)

        //assert
        val expected = listOf(ItemModel(key = "125", value = "Second"),
            ItemModel(key = "107", value = "First"),
             ItemModel(key = "130", value = "Third")
        )

        Assert.assertEquals(expected, result)
    }

    @Test
    fun `convertJsonToArray() with empty object then return empty list`() {

        //arrange
        val startingJson = "{}"

        val stringJsonObj = JSONObject(startingJson)
        val list = mutableListOf<ItemModel>()
        for (key in stringJsonObj.keys()) {
            val value = stringJsonObj.opt(key as String) as String
            list.add(ItemModel(key, value))
        }

        //act
        val result = Utils.convertJsonToArray(stringJsonObj)

        //assert
        val expected = listOf<ItemModel>()

        Assert.assertEquals(expected, result)
    }
}