export const mockedContext = {
  state: {
    redisKey: 'hooq:webview',
    request: {},
    query: {},
    isStaging: true,
    logger: {
      key: 'hooq-webview'
    },
    cookies: {
      GRAB_SESSION:
        '3b361d28c0fd35ec108b1c8c583853644a97706e32b79bf488e201c26a2bf984'
    },
    requestId: '7538e36f-c0d4-44e3-aa1e-13bace627260',
    dummyAccount: {
      userId: '3052ca1d-2502-4ee3-9bbf-92bb4bba2e7f',
      deviceId: 'd5ac1b03-c300-44bb-9b40-d834f6778f67'
    },
    partnerId: 'grab',
    partnerConfiguration: {
      ID: 'dcbf9f5d-493e-4757-ba7f-20cddc37eb78',
      PartnerId: 'grab',
      ChannelPartnerID: 'ID-GRAB',
      HMACKeys: {
        Key10: '9aHFkfXXDrJ5OuABGNZ4yHATPNC5GL',
        Key21: 'Qp26dwSwH2Gz4TrAsiFKVDE7v1tmj8',
        Key29: 'KBwdCJa8Up7GWTxHHFPE5wbdH7lOhk',
        Key34: 'fxVVbX6FniKJtVcBNq4MfxqP6CJGWB',
        Key37: 'ycO98hn37zSHftRmDhAafPGaVArZcO',
        Key43: '6mpPnkhaL44zKBniON3LojacavCQoL',
        Key60: '36pn3uIDq4512MUHE7IwLto1opattp',
        Key7: 'LTP9BUfowm9voleShwbBldKpQV2mut',
        Key92: '7TwoSi8qS4FUCKM2lDpKFqFjG8cOrl'
      },
      IsActive: true,
      CustomConfig: {
        actions: {
          payment: {
            baseUrl:
              'https://selfcare-singtel-stag.evergent.com/myplan/changepayment',
            consent: {
              cta: [
                {
                  color: '#fcbb21',
                  label: 'consentWatch',
                  sku: '@constants.defaultSku.serviceID',
                  type: 'CREATE_PAYMENT_URL'
                },
                {
                  color: '#fffff',
                  label: 'consentPlanList',
                  type: 'CREATE_PLAN_URL'
                }
              ],
              imgHeader:
                'https://grab-static.hooq.tv/images/id/consent/poster.jpg',
              partnerLogo:
                'https://grab-static.hooq.tv/images/id/consent/ic_grab_logo.png'
            },
            externalToken: '@session.partnerToken',
            failedPaymentModal: {
              cta: {
                backgroundColor: '#951a80',
                color: '#ffffff',
                translation: 'payment_failed_cta_label'
              }
            }
          },
          setup: {
            customSessionPayload: {
              partnerToken: '@request.query.id_token'
            },
            expiry: 604800,
            primaryId: {
              cpCustomerId: '@grab.sub'
            }
          }
        },
        constants: {
          appUrl: 'http://grab.hooq.local:4352',
          application: {
            name: 'default',
            secret: 'uW0NUIYdj9GtpRkHTNUXDRecW48RALNU'
          },
          cache: {
            defaultExpiry: 3600,
            key: 'webview:grab'
          },
          channelPartnerId: 'ID-GRAB',
          conviva: {
            appName: 'HOOQ Webview Grab Live TV',
            customerKey: '99e3a6f1e32899dccf037739a1fee7e4005831e0',
            enable: true,
            enableLog: true,
            gatewayUrl: 'https://hooq-grab-test.testonly.conviva.com/'
          },
          cookie: {
            domain: {
              enable: true,
              expiry: 604800000,
              overwrite: true,
              url: 'grab.hooq.local'
            }
          },
          credential: {
            password: 'GR4BlMPjGrzgeV^',
            username: 'grab'
          },
          defaultSku: {
            ccServiceID: 'ID-PAID01-ONETIME-GRAB-CC',
            duration: 1,
            isRenewal: false,
            price: '3.900',
            serviceID: 'ID-PAID01-ONETIME-GRAB-GRABPAY'
          },
          gtm: {
            appName: 'GRAB-WEBVIEW',
            id: 'GTM-546WKLQ'
          },
          locale: 'in_ID',
          logger: {
            key: 'id-grab-webview'
          },
          partnerId: 'grab',
          planSelectorSKU: [
            {
              ccServiceID: 'ID-PAID01-ONETIME-GRAB-CC',
              duration: 1,
              isAvailable: true,
              isRenewal: false,
              plan: 'paid1d',
              price: '3.900',
              serviceID: 'ID-PAID01-ONETIME-GRAB-GRABPAY'
            },
            {
              ccServiceID: 'ID-PAID07-ONETIME-GRAB-CC',
              duration: 7,
              isAvailable: true,
              isRenewal: false,
              plan: 'paid7d',
              price: '24.000',
              serviceID: 'ID-PAID07-ONETIME-GRAB-GRABPAY'
            },
            {
              ccServiceID: 'ID-PAID30-ONETIME-GRAB-CC',
              duration: 30,
              isAvailable: true,
              isRenewal: false,
              plan: 'paid30d',
              price: '69.000',
              serviceID: 'ID-PAID30-ONETIME-GRAB-GRABPAY'
            },
            {
              ccServiceID: 'ID-PAID90-ONETIME-GRAB-CC',
              duration: 90,
              isAvailable: true,
              isRenewal: false,
              plan: 'paid90d',
              price: '100.000',
              serviceID: 'ID-PAID90-ONETIME-GRAB-GRABPAY'
            }
          ],
          redirection: {
            enable: true,
            url: 'http://grab.hooq.local:4352'
          },
          staticAssetUrl: 'https://grab-static.hooq.tv',
          translation: 'https://grab-static.hooq.tv/translation/id.json'
        },
        middlewares: {
          authorization: {
            dummyAccount: {
              acr: ['service:PASSENGER'],
              aud: '08044981144746ec80a870c605fe705b',
              did: '@dummyAccount.deviceId',
              exp: 1541121066,
              iat: 1540861866,
              iss: 'https://idp.grab.com',
              jti: 'GPrwi0qMQ2Cnl5esz4C2kA',
              nbf: 1540861866,
              nonce: '4zMMjv99SuPn11vL',
              pid: '6de31ff0-1502-493d-90da-b49e42774c59',
              sub: '@dummyAccount.userId',
              svc: 'PASSENGER'
            },
            input: {
              baseUrl:
                'https://api.stg-myteksi.com/grabid/v1/oauth2/id_tokens/token_info',
              header: {
                'Content-Type': 'application/json'
              },
              method: 'get',
              payload: {
                client_id: '43235be85f5849b39ded901e4bda14fc',
                id_token: '@request.query.id_token'
              }
            },
            output: '@grab',
            primaryId: '@dummyAccount.userId',
            restriction: {
              mandatory: ['request.query.id_token']
            },
            stagingToken: 'HOOQxGRAB'
          },
          device: {
            deviceId: '@grab.did'
          },
          geo: {
            input: {
              defaultTestCountry: 'ID',
              defaultTestIp: '202.138.229.73',
              supportedCountry: ['ID']
            },
            output: '@geo'
          },
          session: {
            cookieKey: 'GRAB_SESSION',
            output: '@session'
          }
        },
        restriction: {
          liveTv: {
            enable: true
          },
          notification: {
            enable: true
          },
          r21: {
            enable: true
          },
          tvod: {
            enable: false
          }
        },
        ui: {
          background: 'white',
          'body-background-color': 'white',
          'body-color': 'grey-lighter',
          'family-sans-serif': ['Roboto', 'Helvetica', 'Arial', 'sans-serif'],
          'grey-lighter': '#dedede',
          link: 'primary',
          'link-focus': 'primary',
          'link-hover': 'primary',
          'link-visited': 'primary',
          primary: 'purple',
          purple: '#673ab7',
          'tabs-border-bottom-color': 'purple',
          'tabs-border-bottom-width': '5px',
          text: 'grey-darker'
        }
      }
    },
    webPartnerConfiguration: {
      ID: 'dcbf9f5d-493e-4757-ba7f-20cddc37eb78',
      PartnerId: 'grab',
      ChannelPartnerID: 'ID-GRAB',
      IsActive: true,
      CustomConfig: {
        constants: {
          appUrl: 'http://grab.hooq.local:4352',
          cache: {
            defaultExpiry: 3600,
            key: 'webview:grab'
          },
          channelPartnerId: 'ID-GRAB',
          conviva: {
            appName: 'HOOQ Webview Grab Live TV',
            customerKey: '99e3a6f1e32899dccf037739a1fee7e4005831e0',
            enable: true,
            enableLog: true,
            gatewayUrl: 'https://hooq-grab-test.testonly.conviva.com/'
          },
          cookie: {
            domain: {
              enable: true,
              expiry: 604800000,
              overwrite: true,
              url: 'grab.hooq.local'
            }
          },
          defaultSku: {
            ccServiceID: 'ID-PAID01-ONETIME-GRAB-CC',
            duration: 1,
            isRenewal: false,
            price: '3.900',
            serviceID: 'ID-PAID01-ONETIME-GRAB-GRABPAY'
          },
          gtm: {
            appName: 'GRAB-WEBVIEW',
            id: 'GTM-546WKLQ'
          },
          locale: 'in_ID',
          logger: {
            key: 'id-grab-webview'
          },
          partnerId: 'grab',
          planSelectorSKU: [
            {
              ccServiceID: 'ID-PAID01-ONETIME-GRAB-CC',
              duration: 1,
              isAvailable: true,
              isRenewal: false,
              plan: 'paid1d',
              price: '3.900',
              serviceID: 'ID-PAID01-ONETIME-GRAB-GRABPAY'
            },
            {
              ccServiceID: 'ID-PAID07-ONETIME-GRAB-CC',
              duration: 7,
              isAvailable: true,
              isRenewal: false,
              plan: 'paid7d',
              price: '24.000',
              serviceID: 'ID-PAID07-ONETIME-GRAB-GRABPAY'
            },
            {
              ccServiceID: 'ID-PAID30-ONETIME-GRAB-CC',
              duration: 30,
              isAvailable: true,
              isRenewal: false,
              plan: 'paid30d',
              price: '69.000',
              serviceID: 'ID-PAID30-ONETIME-GRAB-GRABPAY'
            },
            {
              ccServiceID: 'ID-PAID90-ONETIME-GRAB-CC',
              duration: 90,
              isAvailable: true,
              isRenewal: false,
              plan: 'paid90d',
              price: '100.000',
              serviceID: 'ID-PAID90-ONETIME-GRAB-GRABPAY'
            }
          ],
          redirection: {
            enable: true,
            url: 'http://grab.hooq.local:4352'
          },
          staticAssetUrl: 'https://grab-static.hooq.tv',
          translation: 'https://grab-static.hooq.tv/translation/id.json'
        },
        restriction: {
          liveTv: {
            enable: true
          },
          notification: {
            enable: true
          },
          r21: {
            enable: true
          },
          tvod: {
            enable: false
          }
        },
        ui: {
          background: 'white',
          'body-background-color': 'white',
          'body-color': 'grey-lighter',
          'family-sans-serif': ['Roboto', 'Helvetica', 'Arial', 'sans-serif'],
          'grey-lighter': '#dedede',
          link: 'primary',
          'link-focus': 'primary',
          'link-hover': 'primary',
          'link-visited': 'primary',
          primary: 'purple',
          purple: '#673ab7',
          'tabs-border-bottom-color': 'purple',
          'tabs-border-bottom-width': '5px',
          text: 'grey-darker'
        }
      }
    },
    geo: {
      ip: '202.138.229.73',
      country: 'ID'
    },
    session: {
      channelPartnerId: 'ID-GRAB',
      session: {
        accessToken: '',
        refreshToken: '',
        deviceSignature:
          'a8be8a97c11997edff610fe2dcda55b5924a844799e189bcb3401635523d680a'
      },
      device: {
        serialNo: '893b746e-118c-4686-931a-16d8b90999d6',
        name: 'HOOQ Webview - v1.0.0',
        type: 'webClient',
        modelNo: 'SM-G900P',
        brand: 'Samsung',
        os: 'Android',
        osVersion: '5.0'
      },
      user: {
        accountNumber: '20190708023941668',
        registerNumber: '20190708023941668',
        cpCustomerId: '6aed61c7-1fb7-45e1-b92b-558fbd1bff35',
        billingMode: 'Monthly',
        billDeliveryType: 'Paper',
        accountStatus: 'Active',
        accountType: 'Residential',
        dmaId: '001',
        dmaName: 'Los Angles',
        salesMethod: 'Call Center',
        directAccount: 'F',
        country: 'ID',
        status: 'SUCCESS',
        deviceId: '893b746e-118c-4686-931a-16d8b90999d6',
        userLevel: 20,
        spAccountId: '6aed61c7-1fb7-45e1-b92b-558fbd1bff35',
        partnerId: 'grab'
      },
      geo: {
        countryOfRegistration: 'ID',
        countryofLogin: 'ID',
        ip: '202.138.229.73'
      },
      subscription: [],
      partnerToken: 'HOOQxGRAB',
      status: 'NEW_USER',
      paymentMethod: ''
    },
    sessionId:
      '3b361d28c0fd35ec108b1c8c583853644a97706e32b79bf488e201c26a2bf984',
    webSession: {
      channelPartnerId: 'ID-GRAB',
      device: {
        serialNo: '893b746e-118c-4686-931a-16d8b90999d6',
        name: 'HOOQ Webview - v1.0.0',
        type: 'webClient',
        modelNo: 'SM-G900P',
        brand: 'Samsung',
        os: 'Android',
        osVersion: '5.0'
      },
      user: {
        accountNumber: '20190708023941668',
        registerNumber: '20190708023941668',
        cpCustomerId: '6aed61c7-1fb7-45e1-b92b-558fbd1bff35',
        billingMode: 'Monthly',
        billDeliveryType: 'Paper',
        accountStatus: 'Active',
        accountType: 'Residential',
        dmaId: '001',
        dmaName: 'Los Angles',
        salesMethod: 'Call Center',
        directAccount: 'F',
        country: 'ID',
        status: 'SUCCESS',
        deviceId: '893b746e-118c-4686-931a-16d8b90999d6',
        userLevel: 20,
        spAccountId: '6aed61c7-1fb7-45e1-b92b-558fbd1bff35',
        partnerId: 'grab'
      },
      geo: {
        countryOfRegistration: 'ID',
        countryofLogin: 'ID',
        ip: '202.138.229.73'
      },
      subscription: [],
      partnerToken: 'HOOQxGRAB',
      status: 'NEW_USER',
      paymentMethod: ''
    }
  }
};
