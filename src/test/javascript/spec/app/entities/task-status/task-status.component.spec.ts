/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import { ToastPlugin } from 'bootstrap-vue';

import * as config from '@/shared/config/config';
import TaskStatusComponent from '@/entities/task-status/task-status.vue';
import TaskStatusClass from '@/entities/task-status/task-status.component';
import TaskStatusService from '@/entities/task-status/task-status.service';
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
  describe('TaskStatus Management Component', () => {
    let wrapper: Wrapper<TaskStatusClass>;
    let comp: TaskStatusClass;
    let taskStatusServiceStub: SinonStubbedInstance<TaskStatusService>;

    beforeEach(() => {
      taskStatusServiceStub = sinon.createStubInstance<TaskStatusService>(TaskStatusService);
      taskStatusServiceStub.retrieve.resolves({ headers: {} });

      wrapper = shallowMount<TaskStatusClass>(TaskStatusComponent, {
        store,
        i18n,
        localVue,
        stubs: { bModal: bModalStub as any },
        provide: {
          taskStatusService: () => taskStatusServiceStub,
          alertService: () => new AlertService(),
        },
      });
      comp = wrapper.vm;
    });

    it('Should call load all on init', async () => {
      // GIVEN
      taskStatusServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

      // WHEN
      comp.retrieveAllTaskStatuss();
      await comp.$nextTick();

      // THEN
      expect(taskStatusServiceStub.retrieve.called).toBeTruthy();
      expect(comp.taskStatuses[0]).toEqual(expect.objectContaining({ id: 123 }));
    });
    it('Should call delete service on confirmDelete', async () => {
      // GIVEN
      taskStatusServiceStub.delete.resolves({});

      // WHEN
      comp.prepareRemove({ id: 123 });
      expect(taskStatusServiceStub.retrieve.callCount).toEqual(1);

      comp.removeTaskStatus();
      await comp.$nextTick();

      // THEN
      expect(taskStatusServiceStub.delete.called).toBeTruthy();
      expect(taskStatusServiceStub.retrieve.callCount).toEqual(2);
    });
  });
});
