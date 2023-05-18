/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import VueRouter from 'vue-router';

import * as config from '@/shared/config/config';
import PersonDetailComponent from '@/entities/person/person-details.vue';
import PersonClass from '@/entities/person/person-details.component';
import PersonService from '@/entities/person/person.service';
import router from '@/router';
import AlertService from '@/shared/alert/alert.service';

const localVue = createLocalVue();
localVue.use(VueRouter);

config.initVueApp(localVue);
const i18n = config.initI18N(localVue);
const store = config.initVueXStore(localVue);
localVue.component('font-awesome-icon', {});
localVue.component('router-link', {});

describe('Component Tests', () => {
  describe('Person Management Detail Component', () => {
    let wrapper: Wrapper<PersonClass>;
    let comp: PersonClass;
    let personServiceStub: SinonStubbedInstance<PersonService>;

    beforeEach(() => {
      personServiceStub = sinon.createStubInstance<PersonService>(PersonService);

      wrapper = shallowMount<PersonClass>(PersonDetailComponent, {
        store,
        i18n,
        localVue,
        router,
        provide: { personService: () => personServiceStub, alertService: () => new AlertService() },
      });
      comp = wrapper.vm;
    });

    describe('OnInit', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        const foundPerson = { id: 123 };
        personServiceStub.find.resolves(foundPerson);

        // WHEN
        comp.retrievePerson(123);
        await comp.$nextTick();

        // THEN
        expect(comp.person).toBe(foundPerson);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        const foundPerson = { id: 123 };
        personServiceStub.find.resolves(foundPerson);

        // WHEN
        comp.beforeRouteEnter({ params: { personId: 123 } }, null, cb => cb(comp));
        await comp.$nextTick();

        // THEN
        expect(comp.person).toBe(foundPerson);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        comp.previousState();
        await comp.$nextTick();

        expect(comp.$router.currentRoute.fullPath).toContain('/');
      });
    });
  });
});
