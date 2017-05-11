package com.appdirect.sdk.appmarket.migration;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.Map;
import java.util.concurrent.Callable;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.appdirect.sdk.appmarket.events.APIResult;
import com.appdirect.sdk.appmarket.events.ErrorCode;

/**
 * Defines the endpoint for operations related to importing existing accounts into the connector.
 * By "migration" we mean in
 */
@Slf4j
@RestController
@RequiredArgsConstructor
public class AppmarketMigrationController {
	private final AppmarketMigrationService migrationService;

	/**
	 * Validate a customer account record that need to be migrated. It is meant to receive the
	 * input of a migration and indicate if it can be migrated.
	 *
	 * @param isvCustomerAccountData A single migration record that is to be validated
	 * @return an {@link APIResult#success(String)} in case the validation is successful and a {@link APIResult#failure(ErrorCode, String)}
	 * otherwise.
	 */
	@RequestMapping(method = POST, value = "/api/v1/migration/validateCustomerAccount", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
	public Callable<APIResult> validateISVCustomerAccount(@RequestBody Map<String, String> isvCustomerAccountData) {
		return () -> migrationService.validateCustomerAccount(isvCustomerAccountData);
	}

	/**
	 * Validate a subscription record that need to be migrated. It is meant to receive the
	 * input of a migration and indicate if it can be migrated.
	 *
	 * @param isvSubscriptionData the subscription record to be migrated
	 * @return an {@link APIResult#success(String)} in case the validation is successful and a {@link APIResult#failure(ErrorCode, String)}
	 * otherwise.
	 */
	@RequestMapping(method = POST, value = "/api/v1/migration/validateSubscription", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
	public Callable<APIResult> validateISVSubscription(@RequestBody Map<String, String> isvSubscriptionData) {
		return () -> migrationService.validateSubscription(isvSubscriptionData);
	}
}