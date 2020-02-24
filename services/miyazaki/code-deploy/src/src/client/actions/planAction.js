export const selectPlan = plan => ({
  type: 'selectPlan',
  payload: plan
});

export const setPlans = plans => ({
  type: 'setPlans',
  payload: plans
});
