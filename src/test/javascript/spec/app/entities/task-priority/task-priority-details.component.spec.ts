/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import VueRouter from 'vue-router';

import * as config from '@/shared/config/config';
import TaskPriorityDetailComponent from '@/entities/task-priority/task-priority-details.vue';
import TaskPriorityClass from '@/entities/task-priority/task-priority-details.component';
import TaskPriorityService from '@/entities/task-priority/task-priority.service';
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
  describe('TaskPriority Management Detail Component', () => {
    let wrapper: Wrapper<TaskPriorityClass>;
    let comp: TaskPriorityClass;
    let taskPriorityServiceStub: SinonStubbedInstance<TaskPriorityService>;

    beforeEach(() => {
      taskPriorityServiceStub = sinon.createStubInstance<TaskPriorityService>(TaskPriorityService);

      wrapper = shallowMount<TaskPriorityClass>(TaskPriorityDetailComponent, {
        store,
        i18n,
        localVue,
        router,
        provide: { taskPriorityService: () => taskPriorityServiceStub, alertService: () => new AlertService() },
      });
      comp = wrapper.vm;
    });

    describe('OnInit', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        const foundTaskPriority = { id: 123 };
        taskPriorityServiceStub.find.resolves(foundTaskPriority);

        // WHEN
        comp.retrieveTaskPriority(123);
        await comp.$nextTick();

        // THEN
        expect(comp.taskPriority).toBe(foundTaskPriority);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        const foundTaskPriority = { id: 123 };
        taskPriorityServiceStub.find.resolves(foundTaskPriority);

        // WHEN
        comp.beforeRouteEnter({ params: { taskPriorityId: 123 } }, null, cb => cb(comp));
        await comp.$nextTick();

        // THEN
        expect(comp.taskPriority).toBe(foundTaskPriority);
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
