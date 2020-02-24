function parsePhoneNumberFromTheFormattedEmail(formattedEmail, regexPattern) {
  if (formattedEmail === undefined) {
    return '';
  }

  const results = formattedEmail.match(regexPattern);
  return results === null ? '' : results[0].replace('+', '');
}

function getUserPhoneNumber(ctx, userDetail) {
  const primaryId =
    ctx.state.partnerConfiguration.CustomConfig.actions.user.primaryId;
  const formattedEmail = userDetail[primaryId.extractFromField];
  const phoneNumber = parsePhoneNumberFromTheFormattedEmail(
    formattedEmail,
    primaryId.rules.regex.pattern
  );

  if (phoneNumber === '') {
    return userDetail;
  }

  const additionalDetail = {
    [primaryId.type]: phoneNumber
  };

  const modifiedUserDetails = {
    ...userDetail,
    ...additionalDetail
  };

  return modifiedUserDetails;
}

function getPlanPropertiesTranslation(sku, translation) {
  let benefits = translation[`plan_${sku.plan}_benefits`]
    ? translation[`plan_${sku.plan}_benefits`].split('|')
    : [];
  benefits = benefits.map(b => b.trim());
  const name = translation[`plan_${sku.plan}_name`];
  return { benefits, name };
}

module.exports = {
  getPlanPropertiesTranslation,
  getUserPhoneNumber,
  parsePhoneNumberFromTheFormattedEmail
};
