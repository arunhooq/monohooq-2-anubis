const session = {
  accessToken:
    'eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1aWQiOiIxODExMDIwNzI1NDcwMTczNDI0ODE0NjkiLCJzaWQiOiIxODExMDIwNzI1NDcwMTczNDI0ODE0NjkiLCJuYmYiOjE1NzUwMTc3MTIsImlzcyI6ImV2IiwibG9jIjoiSUQiLCJpYXQiOjE1NzUwMTc3MTcsImNpZCI6IjM0MjQ5OTE5NyIsImxvZ2luVHlwZSI6IkNvbnRhY3RVc2VyTmFtZSIsInZzdGF0dXMiOmZhbHNlLCJleHAiOjE1NzUwMjQ5MTcsInNlcmlhbE5vIjoidGVzdC1kZXZpY2UtaWQtdXNyLTEyMzQ1IiwibW9kZWxObyI6IkRFRkFVTFQiLCJjcCI6IkhPT1FJTkRPIiwiZGV2aWNlTmFtZSI6IkRFRkFVTFQiLCJkZXZpY2VUeXBlIjoiREVGQVVMVCIsImF1ZCI6IkhPT1EiLCJqdGkiOiJaRTI5LWM3eGctZlBQUy1mcFhDLUlQckYtTlUxMi1nYiJ9.WdIsfCbsg0AyJNF52T_SmE3S25NZ93_l7k6d9eXa4s-4LAC7hQDI_o0qQxGs-87y5vqbOuDUNLGaLEzWXat7I4VRqYUfv1g79E0cYsH9I6NV_vhW_foGjR19XoG7-u9cjSSeOXs1GW0lxmN89WtG99pVx5og2WdaeZZfIv5WeX0510mg4kSnUarU9hY1O-n8myCJGRLAu0o9FvBN3rLP1-hsFuDEhZjNSAp_Y0nWbvwG6zdwzVpgooifp8tCzU5lu5reMFcrILbzU0TTwnCTWOR7ugawYKxCvpqMMIuPl8b8kr-QCS5R-hjdVg-LOj8J_mpSpve8UD1BVz7EuQmUV9ZnZh8_bH6bbaf5819Jw0y6F-XA5KQvQt1i1bY1V2ymIxt_jj1RAd3W2zB6MTNNLTGGW2mOK-FDHZc_2nSOhn26tUhcog8gm4Xo9OyzsfvMNscAl-6IdytArsUKA41lgPNDY6hIcgD1o0NKAxphFc_GFFC9nQ6z5LXhXd74XgX91_bbf-DYzv1I7mVOzEJ3nGHzhdSONv7TKD7iPZghWUpyxMqwzGspk2lL91g3yDXjDiajIHzh16Glv6uS1hOY6vCychqsFXBTj-SoDzMKmQJuP2iFqrCQDDBQ05_kuroDwnZ94GXeMVRd5gJSk1RybLPuIDpgvbCa0KJvpUpH6CA',
  refreshToken: ''
};
const ctx = {
  state: {
    Api: {
      get: () => {}
    },
    partnerConfiguration: {
      CustomConfig: {
        constants: {
          partnerId: 'partnerId'
        }
      }
    },
    session: {
      accessToken:
        'eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1aWQiOiIxODExMDIwNzI1NDQwNjUzNDI0NzQ2MDciLCJzaWQiOiIxODExMDIwNzI1NDQwNjUzNDI0NzQ2MDciLCJuYmYiOjE1NzUwMjE0NjYsImlzcyI6ImV2IiwibG9jIjoiU0ciLCJpYXQiOjE1NzUwMjE0NzEsImNpZCI6IjM0MjQ5MjM4NSIsImxvZ2luVHlwZSI6IkNvbnRhY3RVc2VyTmFtZSIsInZzdGF0dXMiOmZhbHNlLCJleHAiOjE1NzUwMjg2NzEsInNlcmlhbE5vIjoidGVzdC1kZXZpY2UtaWQtdXNyLTEyMzQ1IiwibW9kZWxObyI6IkRFRkFVTFQiLCJjcCI6IkhPT1FTR1AiLCJkZXZpY2VOYW1lIjoiREVGQVVMVCIsImRldmljZVR5cGUiOiJERUZBVUxUIiwiYXVkIjoiSE9PUSIsImp0aSI6IkRTQVEtV3A2Yi03NGdaLUxnYXQtWWlBVS1WVzJ5LXJuIn0.YrzBo4cOT6W9aO6Oo95xpBDAaZ_PLKjnHBbd0SonGBaFLj7JgUZ_ASTZ20TTIdt-Je5vsSoAtpBYfQ1sllpCAfcE7EBj06sPDyU4998WLbrLthtci5K0uB8B0YbXBxC2HpJl1IURdBcanr4rTSOh4cr769X-lJEIP2LpDmjYG-LtIyD37WL15HTJ5oply5UpuXS4ibnuz8lz23NUyRevFhFtkgAMLVbu30ee_XawI6kx6LGeabL-5XL6pBjMBHYVT6fCcgxrEFiVMSzZ2P8XClBCwlNyWsfRjiiFiGtYfJMjo0rhb_jvb-lQ8bXp7HuSB3jUn4tx2HDrCWed21at3Jd24uV9kW7N4QoAEKen2LWodiJ6ixQ_VNO1T34MgArFLmMN3RcYrbqg7F5rqOnJDuNHo__zDGsnQJ3TOX0BXPoaFzTryPeTDxm9TKOexpMh93oYuTVYaDEkf404Te-1rK-SWbLsNICzpDD0Cm49VweTSVmDecpZgaFK3sIWW8Yhh-WbDSH-f7c0lwxhPibISxsAXl2WpkSRp4S_carrICGPECg9PXjiQfRlTUF7VdFbQ0AU_Eg8l1cpbe8BmsiLKJJH6Fae55OsJI_uhVPgTNneqaP3BW8uDad7mb4U0SH_LlMzYVkiFcu_gSOKFwkpU1VZVDmdaEu4gl8FMEZsDO8',
      refreshToken: ''
    }
  }
};

export const testTable = {
  TestProvidedSessionNotVisitor: {
    ctx,
    session,
    visitorToken: undefined,
    expected: {
      partnerId: 'partnerId',
      spAccountId: '181102072547017342481469'
    }
  },
  TestProvidedSessionVisitor: {
    ctx,
    session,
    visitorToken: {
      visitorId: 'visitorId'
    },
    expected: {
      partnerId: 'partnerId',
      visitorId: 'visitorId'
    }
  },
  TestContextSessionNotVisitor: {
    ctx,
    session: undefined,
    visitorToken: undefined,
    expected: {
      partnerId: 'partnerId',
      spAccountId: '181102072544065342474607'
    }
  },
  TestContextSessionVisitor: {
    ctx,
    session: undefined,
    visitorToken: 'visitorId',
    expected: {
      partnerId: 'partnerId',
      visitorId: 'visitorId'
    }
  }
};
