/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.trino.plugin.iceberg.catalog.nessie;

import com.google.common.collect.ImmutableMap;
import org.junit.jupiter.api.Test;

import java.net.URI;
import java.util.Map;

import static io.airlift.configuration.testing.ConfigAssertions.assertFullMapping;
import static io.airlift.configuration.testing.ConfigAssertions.assertRecordedDefaults;
import static io.airlift.configuration.testing.ConfigAssertions.recordDefaults;

public class TestIcebergNessieCatalogConfig
{
    @Test
    public void testDefaults()
    {
        assertRecordedDefaults(recordDefaults(IcebergNessieCatalogConfig.class)
                .setDefaultWarehouseDir(null)
                .setServerUri(null)
                .setDefaultReferenceName("main"));
    }

    @Test
    public void testExplicitPropertyMapping()
    {
        Map<String, String> properties = ImmutableMap.<String, String>builder()
                .put("iceberg.nessie-catalog.default-warehouse-dir", "/tmp")
                .put("iceberg.nessie-catalog.uri", "http://localhost:xxx/api/v1")
                .put("iceberg.nessie-catalog.ref", "someRef")
                .buildOrThrow();

        IcebergNessieCatalogConfig expected = new IcebergNessieCatalogConfig()
                .setDefaultWarehouseDir("/tmp")
                .setServerUri(URI.create("http://localhost:xxx/api/v1"))
                .setDefaultReferenceName("someRef");

        assertFullMapping(properties, expected);
    }
}
