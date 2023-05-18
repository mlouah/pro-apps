/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import Router from 'vue-router';
import { ToastPlugin } from 'bootstrap-vue';

import * as config from '@/shared/config/config';
import TaskPriorityUpdateComponent from '@/entities/task-priority/task-priority-update.vue';
import TaskPriorityClass from '@/entities/task-priority/task-priority-update.component';
import TaskPriorityService from '@/entities/task-priority/task-priority.service';

import TaskService from '@/entities/task/task.service';
import AlertService from '@/shared/alert/alert.service';

const localVue = createLocalVue();

config.initVueApp(localVue);
const i18n = config.initI18N(localVue);
const store = config.initVueXStore(localVue);
const router = new Router();
localVue.use(Router);
localVue.use(ToastPlugin);
localVue.component('font-awesome-icon', {});
localVue.component('b-input-group', {});
localVue.component('b-input-group-prepend', {});
localVue.component('b-form-datepicker', {});
localVue.component('b-form-input', {});

describe('Component Tests', () => {
  describe('TaskPriority Management Update Component', () => {
    let wrapper: Wrapper<TaskPriorityClass>;
    let comp: TaskPriorityClass;
    let taskPriorityServiceStub: SinonStubbedInstance<TaskPriorityService>;

    beforeEach(() => {
      taskPriorityServiceStub = sinon.createStubInstance<TaskPriorityService>(TaskPriorityService);

      wrapper = shallowMount<TaskPriorityClass>(TaskPriorityUpdateComponent, {
        store,
        i18n,
        localVue,
        router,
        provide: {
          taskPriorityService: () => taskPriorityServiceStub,
          alertService: () => new AlertService(),

          taskService: () =>
            sinon.createStubInstance<TaskService>(TaskService, {
              retrieve: sinon.stub().resolves({}),
            } as any),
        },
      });
      comp = wrapper.vm;
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', async () => {
        // GIVEN
        const entity = { id: 123 };
        comp.taskPriority = entity;
        taskPriorityServiceStub.update.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(taskPriorityServiceStub.update.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        comp.taskPriority = entity;
        taskPriorityServiceStub.create.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(taskPriorityServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        const foundTaskPriority = { id: 123 };
        taskPriorityServiceStub.find.resolves(foundTaskPriority);
        taskPriorityServiceStub.retrieve.resolves([foundTaskPriority]);

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
