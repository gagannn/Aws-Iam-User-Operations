package com.iam.user.operations;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.identitymanagement.AmazonIdentityManagement;
import com.amazonaws.services.identitymanagement.AmazonIdentityManagementClientBuilder;
import com.amazonaws.services.identitymanagement.model.CreateAccessKeyRequest;
import com.amazonaws.services.identitymanagement.model.CreateAccessKeyResult;
import com.amazonaws.services.identitymanagement.model.NoSuchEntityException;

public class CreateIAMAccessKey {
	public static void main(String[] args) {

		try {
			String user = "java-user-updated";

			final AmazonIdentityManagement iam = AmazonIdentityManagementClientBuilder.standard()
					.withCredentials(
							new AWSStaticCredentialsProvider(new BasicAWSCredentials("access-key", "secret-key")))
					.withRegion(Regions.EU_CENTRAL_1).build();

			CreateAccessKeyRequest request = new CreateAccessKeyRequest().withUserName(user);

			CreateAccessKeyResult response = iam.createAccessKey(request);

			System.out.println("Created access key: " + response.getAccessKey());
		} catch (NoSuchEntityException e) {
			System.out.println(e);
		}
	}
}
