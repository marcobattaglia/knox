/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.hadoop.gateway.shell.yarn;

import com.jayway.jsonpath.JsonPath;
import org.apache.hadoop.gateway.shell.AbstractRequest;
import org.apache.hadoop.gateway.shell.BasicResponse;
import org.apache.hadoop.gateway.shell.Hadoop;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;

import java.io.IOException;
import java.util.concurrent.Callable;

public class NewApp {

    static class Request extends AbstractRequest<Response> {

        protected Request(Hadoop session) {
            super(session);
        }

        @Override
        protected Callable<Response> callable() {
            return new Callable<Response>() {
                @Override
                public Response call() throws Exception {
                    URIBuilder uri = uri( Yarn.SERVICE_PATH, "/v1/cluster/apps/new-application");
                    HttpPost request = new HttpPost( uri.build() );
                    return new Response( execute( request ) );
                }
            };
        }
    }

    static class Response extends BasicResponse {

        public Response(HttpResponse response) {
            super(response);
        }

        public String getAppId() throws IOException {
            return JsonPath.read(getString(), "$.application-id");
        }
    }
}
