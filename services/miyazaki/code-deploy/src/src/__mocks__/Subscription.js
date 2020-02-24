export const mockedSubscription = {
  data: [
    {
      serviceName: 'GRAB - 90 days of HOOQ',
      serviceID: 'ID-GRAB-ONETIME90D-GRAB',
      startDate: 1550620800000,
      validityTill: 1558406852000,
      status: 'Active',
      retailPrice: 100000,
      duration: '90'
    },
    {
      serviceName: 'GRAB - 90 days of HOOQ',
      serviceID: 'ID-GRAB-ONETIME90D-GRAB',
      startDate: 1550793600000,
      validityTill: 1558578514000,
      status: 'Final Bill',
      retailPrice: 100000,
      duration: '90'
    },
    {
      serviceName: 'GRAB - 90 days of HOOQ',
      serviceID: 'ID-GRAB-ONETIME90D-GRAB',
      serviceOfType: 'PayTV',
      startDate: 1550620800000,
      validityTill: 1558406852000,
      status: 'Active',
      retailPrice: 100000,
      duration: '90'
    },
    ,
    {
      serviceName: 'GRAB - 90 days of HOOQ',
      serviceID: 'ID-GRAB-ONETIME90D-GRAB',
      startDate: 1550620800000,
      validityTill: 1558406852000,
      status: 'Active',
      retailPrice: 100000,
      duration: '90',
      VODItemMsg: []
    }
  ]
};

export const mockedEmptySubscription = {
  data: []
};

export const mockedActiveSubscription = [
  {
    serviceName: 'GRAB - 90 days of HOOQ',
    serviceID: 'ID-GRAB-ONETIME90D-GRAB',
    startDate: 1550620800000,
    validityTill: 1558406852000,
    status: 'Active',
    retailPrice: 100000,
    duration: '90'
  }
];

export const mockedFinalBillSubscription = [
  {
    serviceName: 'GRAB - 90 days of HOOQ',
    serviceID: 'ID-GRAB-ONETIME90D-GRAB',
    startDate: 1550793600000,
    validityTill: 1558578514000,
    status: 'Final Bill',
    retailPrice: 100000,
    duration: '90'
  }
];

export const mockedInactiveSubscription = [];

export const mockedEligibleForTrial = {
  data: {
    message: 'Free Trial Available',
    responseCode: '1',
    transactionId: '26064f9a-46b1-45e0-9f3e-64dbb5f75849',
    status: 'SUCCESS'
  }
};

export const mockedIneligibleForTrial = {
  error: {
    status: 400,
    code: 'AU-eV2155',
    detail: 'No free trial Available'
  }
};
