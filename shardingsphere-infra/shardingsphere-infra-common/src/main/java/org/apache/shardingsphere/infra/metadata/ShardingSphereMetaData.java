/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.shardingsphere.infra.metadata;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.apache.shardingsphere.infra.config.RuleConfiguration;
import org.apache.shardingsphere.infra.metadata.resource.ShardingSphereResource;
import org.apache.shardingsphere.infra.rule.ShardingSphereRule;
import org.apache.shardingsphere.infra.schema.model.ShardingSphereSchema;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Collection;

/**
 * ShardingSphere meta data.
 */
@RequiredArgsConstructor
@Getter
public final class ShardingSphereMetaData {
    
    private final String name;
    
    private final Collection<RuleConfiguration> configurations;
    
    private final Collection<ShardingSphereRule> rules;
    
    private final ShardingSphereResource resource;
    
    private final ShardingSphereSchema schema;
    
    /**
     * Is complete schema context.
     *
     * @return is complete schema context or not
     */
    public boolean isComplete() {
        return !rules.isEmpty() && !resource.getDataSources().isEmpty();
    }
    
    /**
     * Close data sources.
     * @param dataSources data sources
     * @throws SQLException exception
     */
    public void closeDataSources(final Collection<String> dataSources) throws SQLException {
        for (String each :dataSources) {
            close(resource.getDataSources().get(each));
        }
    }
    
    private void close(final DataSource dataSource) throws SQLException {
        if (dataSource instanceof AutoCloseable) {
            try {
                ((AutoCloseable) dataSource).close();
            // CHECKSTYLE:OFF
            } catch (final Exception e) {
            // CHECKSTYLE:ON
                throw new SQLException(e);
            }
        }
    }
}
