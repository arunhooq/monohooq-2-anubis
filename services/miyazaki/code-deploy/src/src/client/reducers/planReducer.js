const defaultState = {
  selectedPlan: {
    benefits: [],
    currency: '',
    duration: 0,
    name: '',
    paymentUrl: '',
    price: null
  },
  plans: []
};

export default function subscriptionReducer(state = defaultState, action) {
  switch (action.type) {
    case 'setPlans':
      const mappedPlans = action.payload.map(item => {
        return {
          benefits: item.benefits,
          currency: item.currency,
          duration: item.duration,
          name: item.name,
          paymentUrl: item.url,
          price: item.price
        };
      });
      return { ...state, plans: mappedPlans };
    case 'selectPlan':
      return { ...state, selectedPlan: action.payload };
    default:
      return state;
  }
}
