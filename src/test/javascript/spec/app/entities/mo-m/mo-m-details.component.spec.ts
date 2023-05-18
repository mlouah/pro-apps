/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import VueRouter from 'vue-router';

import * as config from '@/shared/config/config';
import MoMDetailComponent from '@/entities/mo-m/mo-m-details.vue';
import MoMClass from '@/entities/mo-m/mo-m-details.component';
import MoMService from '@/entities/mo-m/mo-m.service';
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
  describe('MoM Management Detail Component', () => {
    let wrapper: Wrapper<MoMClass>;
    let comp: MoMClass;
    let moMServiceStub: SinonStubbedInstance<MoMService>;

    beforeEach(() => {
      moMServiceStub = sinon.createStubInstance<MoMService>(MoMService);

      wrapper = shallowMount<MoMClass>(MoMDetailComponent, {
        store,
        i18n,
        localVue,
        router,
        provide: { moMService: () => moMServiceStub, alertService: () => new AlertService() },
      });
      comp = wrapper.vm;
    });

    describe('OnInit', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        const foundMoM = { id: 123 };
        moMServiceStub.find.resolves(foundMoM);

        // WHEN
        comp.retrieveMoM(123);
        await comp.$nextTick();

        // THEN
        expect(comp.moM).toBe(foundMoM);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        const foundMoM = { id: 123 };
        moMServiceStub.find.resolves(foundMoM);

        // WHEN
        comp.beforeRouteEnter({ params: { moMId: 123 } }, null, cb => cb(comp));
        await comp.$nextTick();

        // THEN
        expect(comp.moM).toBe(foundMoM);
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
