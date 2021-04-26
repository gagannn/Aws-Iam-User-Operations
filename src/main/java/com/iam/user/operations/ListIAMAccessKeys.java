package com.iam.user.operations;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.identitymanagement.AmazonIdentityManagement;
import com.amazonaws.services.identitymanagement.AmazonIdentityManagementClientBuilder;
import com.amazonaws.services.identitymanagement.model.AccessKeyMetadata;
import com.amazonaws.services.identitymanagement.model.ListAccessKeysRequest;
import com.amazonaws.services.identitymanagement.model.ListAccessKeysResult;
import com.amazonaws.services.identitymanagement.model.NoSuchEntityException;

public class ListIAMAccessKeys {
	public static void main(String[] args) {
		try {
			String username = "java-user-updated";

			final AmazonIdentityManagement iam = AmazonIdentityManagementClientBuilder.standard()
					.withCredentials(
							new AWSStaticCredentialsProvider(new BasicAWSCredentials("access-key", "secret-key")))
					.withRegion(Regions.EU_CENTRAL_1).build();
			boolean done = false;
			ListAccessKeysRequest request = new ListAccessKeysRequest().withUserName(username);

			while (!done) {

				ListAccessKeysResult response = iam.listAccessKeys(request);
				for (AccessKeyMetadata metadata : response.getAccessKeyMetadata()) {
					System.out.format("Retrieved access key %s\n", metadata.getAccessKeyId());
				}

				request.setMarker(response.getMarker());

				if (!response.getIsTruncated()) {
					done = true;
				}
			}
		} catch (NoSuchEntityException e) {
			System.out.println(e);
		}
	}
}
