describe('Discover Page Scroll Restoration', () => {
  Cypress.Cookies.debug(true);

  it('Should restore scroll position after navigate to another page', () => {
    const scrollPosition = {
      x: 0,
      y: 500
    };

    cy.server();

    cy.route(
      'GET',
      'https://cdn-discover.hooq.tv/v1.5/discover/feed',
      'fixture:discover'
    );
    cy.route('GET', '/subscription/status', 'fixture:subscription');
    cy.route('GET', '/playlist/watched', 'fixture:watched');
    cy.route(
      'GET',
      'https://cdn-discover.hooq.tv/v1.5/discover/curation',
      'fixture:movieCurations'
    );

    cy.InitializeToken();

    cy.wait(5000);
    cy.scrollTo(scrollPosition.x, scrollPosition.y);
    cy.wait(1000);

    cy.get(
      '#__next > div.tab-wrapper > div > ul > li:nth-child(3) > a'
    ).click();
    cy.wait(3000);

    cy.get(
      '#__next > div.tab-wrapper > div > ul > li:nth-child(1) > a'
    ).click();
    cy.wait(3000);

    cy.window().then($window => {
      expect($window.scrollY).to.equal(scrollPosition.y);
    });
  });
});