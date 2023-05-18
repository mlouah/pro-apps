/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import VueRouter from 'vue-router';

import * as config from '@/shared/config/config';
import TaskStatusDetailComponent from '@/entities/task-status/task-status-details.vue';
import TaskStatusClass from '@/entities/task-status/task-status-details.component';
import TaskStatusService from '@/entities/task-status/task-status.service';
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
  describe('TaskStatus Management Detail Component', () => {
    let wrapper: Wrapper<TaskStatusClass>;
    let comp: TaskStatusClass;
    let taskStatusServiceStub: SinonStubbedInstance<TaskStatusService>;

    beforeEach(() => {
      taskStatusServiceStub = sinon.createStubInstance<TaskStatusService>(TaskStatusService);

      wrapper = shallowMount<TaskStatusClass>(TaskStatusDetailComponent, {
        store,
        i18n,
        localVue,
        router,
        provide: { taskStatusService: () => taskStatusServiceStub, alertService: () => new AlertService() },
      });
      comp = wrapper.vm;
    });

    describe('OnInit', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        const foundTaskStatus = { id: 123 };
        taskStatusServiceStub.find.resolves(foundTaskStatus);

        // WHEN
        comp.retrieveTaskStatus(123);
        await comp.$nextTick();

        // THEN
        expect(comp.taskStatus).toBe(foundTaskStatus);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        const foundTaskStatus = { id: 123 };
        taskStatusServiceStub.find.resolves(foundTaskStatus);

        // WHEN
        comp.beforeRouteEnter({ params: { taskStatusId: 123 } }, null, cb => cb(comp));
        await comp.$nextTick();

        // THEN
        expect(comp.taskStatus).toBe(foundTaskStatus);
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
