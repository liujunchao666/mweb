package com.ibm.service.impl;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.dataflow.DataflowJob;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MyElasticDataflowJob implements DataflowJob<String> {

    int status=0;
    @Override
    public List<String> fetchData(ShardingContext context) {
        String jobParameter = context.getShardingParameter();


        switch (context.getShardingItem()) {
            case 0:
                 status=0;
                List<String> data1 = new ArrayList<String>();
                Random rand = new Random();
                boolean b = rand.nextBoolean();
                if( b){
                    data1.add("jobParameter   : :"+jobParameter +" "+b+"计算理论库存成功，修改任务状态为1");
                    status=1;
                    return data1;
                }else{
                    System.out.println("jobParameter   : :"+jobParameter +" "+b+"计算理论库存失败");
                    return null;
                }
            case 1:
                if(status==1) {
                    List<String> data2 = new ArrayList<String>();
                    Random rand2 = new Random();
                    boolean b2 = rand2.nextBoolean();

                    if( b2){
                        data2.add("jobParameter   : :"+jobParameter +" "+b2+"计算实际库存，修改任务状态为2");
                        status=2;
                        return data2;
                    }else{
                        System.out.println("jobParameter   : :"+jobParameter +" "+b2+"计算实际库存失败");
                        return null;
                    }
                }

            case 2:
                if(status==2){
                    Random rand3 = new Random();
                    boolean b3= rand3.nextBoolean();
                    List<String> data3 = new ArrayList<String>();
                    if( b3){
                        data3.add("jobParameter   : :"+jobParameter +" "+b3+"生成补货单成功，修改任务状态为3");
                        status=3;
                        return data3;

                    }else{
                        System.out.println("jobParameter   : :"+jobParameter +" "+b3+"生成补货单失败");
                        return null;

                    }
                }

        }
        return null;
    }

    @Override
    public void processData(ShardingContext context, List<String> data) {

        for (String string : data) {
            System.out.println(string);
        }

    }
}
