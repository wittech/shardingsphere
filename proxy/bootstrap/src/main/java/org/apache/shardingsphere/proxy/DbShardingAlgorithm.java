package org.apache.shardingsphere.proxy;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Properties;
import org.apache.shardingsphere.sharding.api.sharding.standard.PreciseShardingValue;
import org.apache.shardingsphere.sharding.api.sharding.standard.RangeShardingValue;
import org.apache.shardingsphere.sharding.api.sharding.standard.StandardShardingAlgorithm;

public class DbShardingAlgorithm implements StandardShardingAlgorithm<String> {

    public String doSharding(Collection<String> availableTargetNames, PreciseShardingValue<String> shardingValue) {
        String dbDs = "ds_0";
        if (shardingValue != null) {
            // String shardingValueToString = shardingValue.toString().replace("PreciseShardingValue(", "").replace(")", "");
            // System.out.println("doSharding----shardingValueToString------" + shardingValueToString);
            // String orgCode = ShardingUtil.convertKeyValueToMap(shardingValueToString).get("value");
            // dbDs = ShardingUtil.getShardingKey(orgCode);
            // System.out.println("doSharding----orgCode------" + orgCode + "----dbDs----" + dbDs);
            return dbDs;
        } else {
            throw new UnsupportedOperationException("分片列为空");
        }
    }

    public Collection<String> doSharding(Collection<String> databaseNamescollection, RangeShardingValue<String> rangeShardingValue) {
        Collection<String> collect = new ArrayList();
        System.out.println("doSharding----rangeShardingValue------" + rangeShardingValue.getValueRange());
        System.out.println("doSharding----databaseNamescollection------" + databaseNamescollection.toString());
        if (rangeShardingValue != null) {
            return collect;
        } else {
            throw new UnsupportedOperationException("分片列为空");
        }
    }
    public Properties getProps() {
        return null;
    }

    public void init(Properties properties) {
        System.out.println("DbShardingAlgorithm-------进入init");
    }
}