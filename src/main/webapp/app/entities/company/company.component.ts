import { mixins } from 'vue-class-component';
import { Component, Vue, Inject } from 'vue-property-decorator';
import Vue2Filters from 'vue2-filters';
import { ICompany } from '@/shared/model/company.model';

import JhiDataUtils from '@/shared/data/data-utils.service';

import CompanyService from './company.service';
import AlertService from '@/shared/alert/alert.service';

@Component({
  mixins: [Vue2Filters.mixin],
})
export default class Company extends mixins(JhiDataUtils) {
  @Inject('companyService') private companyService: () => CompanyService;
  @Inject('alertService') private alertService: () => AlertService;

  private removeId: number = null;

  public companies: ICompany[] = [];

  public isFetching = false;

  public mounted(): void {
    this.retrieveAllCompanys();
  }

  public clear(): void {
    this.retrieveAllCompanys();
  }

  public retrieveAllCompanys(): void {
    this.isFetching = true;
    this.companyService()
      .retrieve()
      .then(
        res => {
          this.companies = res.data;
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

  public prepareRemove(instance: ICompany): void {
    this.removeId = instance.id;
    if (<any>this.$refs.removeEntity) {
      (<any>this.$refs.removeEntity).show();
    }
  }

  public removeCompany(): void {
    this.companyService()
      .delete(this.removeId)
      .then(() => {
        const message = this.$t('proAppsApp.company.deleted', { param: this.removeId });
        this.$bvToast.toast(message.toString(), {
          toaster: 'b-toaster-top-center',
          title: 'Info',
          variant: 'danger',
          solid: true,
          autoHideDelay: 5000,
        });
        this.removeId = null;
        this.retrieveAllCompanys();
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
