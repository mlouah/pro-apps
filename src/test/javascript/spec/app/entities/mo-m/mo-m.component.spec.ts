/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import { ToastPlugin } from 'bootstrap-vue';

import * as config from '@/shared/config/config';
import MoMComponent from '@/entities/mo-m/mo-m.vue';
import MoMClass from '@/entities/mo-m/mo-m.component';
import MoMService from '@/entities/mo-m/mo-m.service';
import AlertService from '@/shared/alert/alert.service';

const localVue = createLocalVue();
localVue.use(ToastPlugin);

config.initVueApp(localVue);
const i18n = config.initI18N(localVue);
const store = config.initVueXStore(localVue);
localVue.component('font-awesome-icon', {});
localVue.component('b-badge', {});
localVue.directive('b-modal', {});
localVue.component('b-button', {});
localVue.component('router-link', {});

const bModalStub = {
  render: () => {},
  methods: {
    hide: () => {},
    show: () => {},
  },
};

describe('Component Tests', () => {
  describe('MoM Management Component', () => {
    let wrapper: Wrapper<MoMClass>;
    let comp: MoMClass;
    let moMServiceStub: SinonStubbedInstance<MoMService>;

    beforeEach(() => {
      moMServiceStub = sinon.createStubInstance<MoMService>(MoMService);
      moMServiceStub.retrieve.resolves({ headers: {} });

      wrapper = shallowMount<MoMClass>(MoMComponent, {
        store,
        i18n,
        localVue,
        stubs: { bModal: bModalStub as any },
        provide: {
          moMService: () => moMServiceStub,
          alertService: () => new AlertService(),
        },
      });
      comp = wrapper.vm;
    });

    it('Should call load all on init', async () => {
      // GIVEN
      moMServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

      // WHEN
      comp.retrieveAllMoMs();
      await comp.$nextTick();

      // THEN
      expect(moMServiceStub.retrieve.called).toBeTruthy();
      expect(comp.moMS[0]).toEqual(expect.objectContaining({ id: 123 }));
    });
    it('Should call delete service on confirmDelete', async () => {
      // GIVEN
      moMServiceStub.delete.resolves({});

      // WHEN
      comp.prepareRemove({ id: 123 });
      expect(moMServiceStub.retrieve.callCount).toEqual(1);

      comp.removeMoM();
      await comp.$nextTick();

      // THEN
      expect(moMServiceStub.delete.called).toBeTruthy();
      expect(moMServiceStub.retrieve.callCount).toEqual(2);
    });
  });
});
