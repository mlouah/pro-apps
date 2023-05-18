/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import Router from 'vue-router';
import { ToastPlugin } from 'bootstrap-vue';

import * as config from '@/shared/config/config';
import TaskStatusUpdateComponent from '@/entities/task-status/task-status-update.vue';
import TaskStatusClass from '@/entities/task-status/task-status-update.component';
import TaskStatusService from '@/entities/task-status/task-status.service';

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
  describe('TaskStatus Management Update Component', () => {
    let wrapper: Wrapper<TaskStatusClass>;
    let comp: TaskStatusClass;
    let taskStatusServiceStub: SinonStubbedInstance<TaskStatusService>;

    beforeEach(() => {
      taskStatusServiceStub = sinon.createStubInstance<TaskStatusService>(TaskStatusService);

      wrapper = shallowMount<TaskStatusClass>(TaskStatusUpdateComponent, {
        store,
        i18n,
        localVue,
        router,
        provide: {
          taskStatusService: () => taskStatusServiceStub,
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
        comp.taskStatus = entity;
        taskStatusServiceStub.update.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(taskStatusServiceStub.update.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        comp.taskStatus = entity;
        taskStatusServiceStub.create.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(taskStatusServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        const foundTaskStatus = { id: 123 };
        taskStatusServiceStub.find.resolves(foundTaskStatus);
        taskStatusServiceStub.retrieve.resolves([foundTaskStatus]);

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
