package com.osipyan.armen;


import com.osipyan.armen.task.Solution;
import org.junit.Assert;
import org.junit.Test;

public class SolutionTest {
    @Test
    public void getResultGet(){

        Assert.assertEquals(10,Solution.getResult("STWSWTPPTPTTPWPP","Human"),1e-9);

    }
}
