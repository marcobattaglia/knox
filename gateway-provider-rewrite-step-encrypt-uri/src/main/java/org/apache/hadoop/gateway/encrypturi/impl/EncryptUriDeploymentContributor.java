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
package org.apache.hadoop.gateway.encrypturi.impl;

import org.apache.hadoop.gateway.deploy.DeploymentContext;
import org.apache.hadoop.gateway.deploy.ProviderDeploymentContributor;
import org.apache.hadoop.gateway.deploy.ProviderDeploymentContributorBase;
import org.apache.hadoop.gateway.descriptor.FilterParamDescriptor;
import org.apache.hadoop.gateway.descriptor.ResourceDescriptor;
import org.apache.hadoop.gateway.encrypturi.api.EncryptUriDescriptor;
import org.apache.hadoop.gateway.services.security.AliasService;
import org.apache.hadoop.gateway.topology.Provider;
import org.apache.hadoop.gateway.topology.Service;

import java.util.List;

public class EncryptUriDeploymentContributor
    extends ProviderDeploymentContributorBase
    implements ProviderDeploymentContributor {

  public static final String PROVIDER_ROLE_NAME = "encrypt";
  public static final String PROVIDER_IMPL_NAME = "default";
  private AliasService as;

  @Override
  public String getRole() {
    return PROVIDER_ROLE_NAME;
  }

  @Override
  public String getName() {
    return PROVIDER_IMPL_NAME;
  }

  public void setAliasService(AliasService as) {
    this.as = as;
  }

  @Override
  public void initializeContribution(DeploymentContext context) {
    super.initializeContribution(context);

    String clusterName = context.getTopology().getName();

    // we don't want to overwrite an existing alias from a previous topology deployment
    // so we can't just blindly generateAlias here.
    // this version of getPassword will generate a value for it only if missing
    this.as.getPasswordFromAliasForCluster(clusterName, EncryptUriDescriptor.PASSWORD_ALIAS, true);
  }

  @Override
  public void contributeProvider( DeploymentContext context, Provider provider ) {
  }

  @Override
  public void contributeFilter(
      DeploymentContext context,
      Provider provider,
      Service service,
      ResourceDescriptor resource,
      List<FilterParamDescriptor> params ) {
  }

}
