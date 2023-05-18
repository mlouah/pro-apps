import { mixins } from 'vue-class-component';
import { Component, Vue, Inject } from 'vue-property-decorator';
import Vue2Filters from 'vue2-filters';
import { IPerson } from '@/shared/model/person.model';

import JhiDataUtils from '@/shared/data/data-utils.service';

import PersonService from './person.service';
import AlertService from '@/shared/alert/alert.service';

@Component({
  mixins: [Vue2Filters.mixin],
})
export default class Person extends mixins(JhiDataUtils) {
  @Inject('personService') private personService: () => PersonService;
  @Inject('alertService') private alertService: () => AlertService;

  private removeId: number = null;

  public people: IPerson[] = [];

  public isFetching = false;

  public mounted(): void {
    this.retrieveAllPersons();
  }

  public clear(): void {
    this.retrieveAllPersons();
  }

  public retrieveAllPersons(): void {
    this.isFetching = true;
    this.personService()
      .retrieve()
      .then(
        res => {
          this.people = res.data;
          this.isFetching = false;
        },
        err => {
          this.isFetching = false;
          this.alertService().showHttpError(this, err.response);
        }
      );
  }

  public handleSyncList(): void {
    this.clear();
  }

  public prepareRemove(instance: IPerson): void {
    this.removeId = instance.id;
    if (<any>this.$refs.removeEntity) {
      (<any>this.$refs.removeEntity).show();
    }
  }

  public removePerson(): void {
    this.personService()
      .delete(this.removeId)
      .then(() => {
        const message = this.$t('proAppsApp.person.deleted', { param: this.removeId });
        this.$bvToast.toast(message.toString(), {
          toaster: 'b-toaster-top-center',
          title: 'Info',
          variant: 'danger',
          solid: true,
          autoHideDelay: 5000,
        });
        this.removeId = null;
        this.retrieveAllPersons();
        this.closeDialog();
      })
      .catch(error => {
        this.alertService().showHttpError(this, error.response);
      });
  }

  public closeDialog(): void {
    (<any>this.$refs.removeEntity).hide();
  }
}
