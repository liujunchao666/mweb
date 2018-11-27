package com.ibm.service.impl;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;

public class MyElasticSimpleJob implements SimpleJob {

    @Override
    public void execute(ShardingContext shardingContext) {
        int shardingItem = shardingContext.getShardingItem();
        int sharCount = shardingContext.getShardingTotalCount();
        String taskId = shardingContext.getTaskId();
        String jobParameter = shardingContext.getShardingParameter();

        System.out.println("shardingItem "+shardingItem +" sharCount :" + sharCount +" taskId: " +taskId +"jobParameter   : :"+jobParameter );

        switch (shardingItem) {
            case 0:
                System.out.println("处理北京大仓");
                break;
            case 1:
                System.out.println("处理天津大仓");
                break;

        }
    }
}
