async function isCustomerEligibleForFreeTrial(ctx) {
  try {
    const opts = {
      path: '/proxy/isCustomerEligibleForFreeTrial'
    };
    return await ctx.state.Api.post(opts);
  } catch (err) {
    return err;
  }
}

module.exports = {
  isCustomerEligibleForFreeTrial
};
